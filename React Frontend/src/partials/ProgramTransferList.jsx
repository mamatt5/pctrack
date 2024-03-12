import { Button, Checkbox, Collapse, Grid, List, ListItemButton, ListItemIcon, ListItemText, Paper } from "@mui/material";
import { useEffect, useState } from "react";
import callApi from "../api/callApi";

/**
 * Get list of softwares through API query
 * @param {function} setSoftwares - Assign API result to 'softwares' variable
 */
const getSoftwares = (setSoftwares) => {
    const config = {
        method: "get",
        endpoint: "softwares"
    }

    callApi(setSoftwares, null, config);
}

/**
 * Get list of programs through API query
 * @param {function} setPrograms - Assign API result to 'programs' variable
 */
const getPrograms = (setPrograms) => {
    const config = {
        method: "get",
        endpoint: "programs"
    }

    callApi(setPrograms, null, config);
}

/**
 * Return the program object with its program ID of 'num' variable
 * @param {Object[]} programs - List of program objects
 * @param {*} num - The program ID looking for
 * @returns {Object} The program object based on num as program ID
 */
const returnProgram = (programs, num) => {
    var object = {};
    programs.map(program => {
        if (program.programId == num) {
            object = program;
        }
    })
    return object;
}

/**
 * The list in left-hand side
 * @param {Object[]} array - The software array available to select, containing list of softwares within database.
 * @param {Object[]} programs - The program array available to select, containing list of programs within database.
 * @param {int[]} open - List of software ID that have their drop-down menu open. E.g. Opening 'Nodejs' menu will push number 3 into the array
 * @param {Object[]} softwaresSelected - Softwares being selected, no duplicate softwares can be pushed
 * @param {Object[]} programsSelected - Programs being selected, will be moved to right-hand side upon clicked to right button
 * @returns {ReactNode} Displaying list of softwares with drop down menu of its different versions
 */
const customList = (array, programs, [open, setOpen], [softwaresSelected, setSoftwaresSelected], [programsSelected, setProgramsSelected]) => {

    const style = {
        py: 0,
        minHeight: 32
    }

    return (
        <Paper sx={{ width: 200, height: 400, overflow: 'auto' }}>
            <List dense component="div" role="list">
                <>
                {
                    array.map(software =>
                        <div key={software.softwareId}>
                            <ListItemButton onClick={() => {
                                if (!open.includes(software.softwareId)) setOpen([...open, software.softwareId]);
                                else setOpen(open.filter(a => a !== software.softwareId));
                            }}>
                                <ListItemText primary={software.name} />
                            </ListItemButton>
                            <Collapse in={open.includes(software.softwareId)}>
                                {
                                    software.versions.map(program =>
                                        <ListItemButton key={program.programId} sx={style} onClick={() => {
                                            if (programsSelected.find((x) => x.programId == program.programId) == undefined) {
                                                // Remove any selected version with the same software
                                                var tempProg = programsSelected.filter(a => a.software.softwareId !== software.softwareId);
                                                tempProg.push(returnProgram(programs, program.programId));

                                                setSoftwaresSelected([...softwaresSelected, software]);
                                                setProgramsSelected(tempProg);
                                            } else {
                                                setSoftwaresSelected(softwaresSelected.filter(a => a.softwareId !== software.softwareId))
                                                setProgramsSelected(programsSelected.filter(a => a.programId !== program.programId));
                                            }

                                        }}>
                                            <ListItemIcon>
                                                <Checkbox checked={programsSelected.find((x) => x.programId == program.programId) != undefined} />
                                            </ListItemIcon>
                                            <ListItemText primary={program.version} />
                                        </ListItemButton>
                                    )
                                }
                            </Collapse>
                        </div>
                    )
                }
                </>
            </List>
        </Paper>
    )
}

/**
 * The list in right-hand side
 * @param {Object[]} array - List of program objects selected from customList, originally hold by programsSelected 
 * @param {Object[]} softwaresDeselected - List of software objects ready to be moved back to left-hand side
 * @returns {ReactNode} Displaying list of programs ready to be pushed into computer object
 */
const confirmList = (array, [softwaresDeselected, setSoftwaresDeselected]) => {
    return (
        <Paper sx={{ width: 200, height: 400, overflow: 'auto' }}>
            <List dense component="div" role="list">
                {
                    array.map(program =>
                        <ListItemButton key={program.programId} onClick={() => {
                            if (softwaresDeselected.find((x) => x.softwareId == program.software.softwareId) == undefined){
                                setSoftwaresDeselected([...softwaresDeselected, program.software]);
                            } else {
                                setSoftwaresDeselected(softwaresDeselected.filter(a => a.softwareId !== program.software.softwareId));
                            }
                        }}>
                            <ListItemIcon>
                                <Checkbox checked={softwaresDeselected.find((x) => x.softwareId == program.software.softwareId) != undefined}/>
                            </ListItemIcon>
                            <ListItemText primary={program.software.name + " " + program.version}/>
                        </ListItemButton>
                    )
                }
            </List>
        </Paper>
    )
}

const ProgramTransferList = (props) => {

    const [softwares, setSoftwares] = useState([]);                     // List of softwares in database
    const [programs, setPrograms] = useState([]);                       // List of programs in database
    const [leftSide, setLeftSide] = useState([]);                       // List of softwares available to select in customList
    const [open, setOpen] = useState([]);                               // List of dropdown menus currently opened - indicated as software IDs
    const [softwaresSelected, setSoftwaresSelected] = useState([]);     // List of softwares selected within customList
    const [programsSelected, setProgramsSelected] = useState([]);       // List of programs selected within customList
    const [softwaresDeselected, setSoftwaresDeselected] = useState([]); // List of softwares selected within confirmList, ready to be moved back
    const [programList, setProgramList] = props.programs;               // List of programs ready to be pushed to computer, or already exists within computer object (Editing only)

    useEffect(() => {
        getSoftwares(setSoftwares);
        getPrograms(setPrograms);
    }, []);

    /**
     * Update customList when software query being updated
     */
    useEffect(() => {
        setLeftSide(softwares);
        if (programList.length != 0) {
            var tempLeft = softwares;
            programList.map(program => {
                tempLeft = tempLeft.filter(a => a.softwareId !== program.software.softwareId);
            });
            setLeftSide(tempLeft);
        }
    }, [softwares]);

    /**
     * Upon clicking right arrow button, moves all programs selected in customList to confirmList
     */
    const handleLeft = () => {
        var tempLeft = leftSide;
        var tempRight = programList;

        programsSelected.map(program => {
            tempRight.push(program);
            tempLeft = tempLeft.filter(a => a.softwareId !== program.software.softwareId);
        })
        setProgramList(tempRight);
        setLeftSide(tempLeft);
        setSoftwaresSelected([]);
        setProgramsSelected([]);
    }

    /**
     * Upon clicking left arrow button, moves all programs selected in confirmList to customList
     */
    const handleRight = () => {
        var tempLeft = leftSide;
        var tempRight = programList;

        softwaresDeselected.map(software => {
            tempLeft.push(software);
            tempRight = tempRight.filter(a => a.software.softwareId !== software.softwareId);
        });

        setProgramList(tempRight);
        setLeftSide(tempLeft);
        setSoftwaresDeselected([]);
    }

    const programsList = () => {

        return (
            <Grid container spacing={2} justifyContent="center" alignItems="center">
                <Grid item>{customList(leftSide, programs, [open, setOpen], [softwaresSelected, setSoftwaresSelected], [programsSelected, setProgramsSelected])}</Grid>
                <Grid item>
                    <Grid container direction={"column"} alignItems={"center"}>
                        <Button
                            sx={{ my: 0.5 }}
                            variant="outlined"
                            size="small"
                            aria-label="move selected right"
                            onClick={(e) => handleLeft()}
                        >
                            &gt;
                        </Button>
                        <Button
                            sx={{ my: 0.5 }}
                            variant="outlined"
                            size="small"
                            aria-label="move selected left"
                            onClick={() => handleRight()}
                        >
                            &lt;
                        </Button>
                      
                    </Grid>
                </Grid>
                <Grid item>{confirmList(programList, [softwaresDeselected, setSoftwaresDeselected])}</Grid>
            </Grid>
        )
    }

    return (
        <>
            {/* Error prevention - in case database is null*/}
            {softwares.length != 0 && programsList()}
        </>

    )
}

export default ProgramTransferList;