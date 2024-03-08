import { useEffect } from "react";
import { useState } from "react";
import callApi from "../api/callApi";
import { Button, Checkbox, Collapse, Grid, List, ListItemButton, ListItemIcon, ListItemText, Paper } from "@mui/material";

const getSoftwares = (setSoftwares) => {
    const config = {
        method: "get",
        endpoint: "softwares"
    }

    callApi(setSoftwares, null, config);
}

const getPrograms = (setPrograms) => {
    const config = {
        method: "get",
        endpoint: "programs"
    }

    callApi(setPrograms, null, config);
}

const returnProgram = (programs, num) => {
    var object = {};
    programs.map(program => {
        if (program.programId == num) {
            object = program;
        }
    })
    return object;
}

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

    const [softwares, setSoftwares] = useState([]);
    const [programs, setPrograms] = useState([]);
    const [leftSide, setLeftSide] = useState([]);
    const [open, setOpen] = useState([]);
    const [softwaresSelected, setSoftwaresSelected] = useState([]);
    const [programsSelected, setProgramsSelected] = useState([]);
    const [softwaresDeselected, setSoftwaresDeselected] = useState([]);
    const [programList, setProgramList] = props.programs;

    useEffect(() => {
        getSoftwares(setSoftwares);
        getPrograms(setPrograms);
    }, []);

    useEffect(() => {
        setLeftSide(softwares);
        if (programList.length != 0) {
            console.log('%c open', 'background: #222; color: #bada55')
            var tempLeft = softwares;
            programList.map(program => {
                tempLeft = tempLeft.filter(a => a.softwareId !== program.software.softwareId);
            });
            setLeftSide(tempLeft);
        }
    }, [softwares]);

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
            {softwares.length != 0 && programsList()}
        </>

    )
}

export default ProgramTransferList;