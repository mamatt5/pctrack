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

const delCheck = (num, programs) => {
    let arr = []
    programs.map(program => {
        if (program.software.softwareId == num) {
            arr.push(program.programId);
        }
    });
    return arr;
}

const customList = (array, [open, setOpen], [softwaresSelected, setSoftwaresSelected], [programsSelected, setProgramsSelected]) => {

    const style = {
        py: 0,
        minHeight: 32
    }

    return (
        <Paper sx={{ width: 200, height: 400, overflow: 'auto' }}>
            <List dense component="div" role="list">
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
                                            // const id = program.programId;
                                            // if (!checked[id]) {
                                            //     var changes = checked;
                                            //     let arr = delCheck(software.softwareId, programs);
                                            //     arr.map(index => {
                                            //         changes[index] = false;
                                            //     })
                                            //     setChecked(changes);
                                            //     setChecked({ ...checked, [program.programId]: true });
                                            //     if (!softwaresSelected.includes(software)) setSoftwaresSelected([...softwaresSelected, software]);
                                            // }
                                            // else {
                                            //     setChecked({ ...checked, [program.programId]: false });
                                            //     setSoftwaresSelected(softwaresSelected.filter(a => a.softwareId !== software.softwareId))
                                            // }
                                            var tempProg = programsSelected.filter(a => a.softwareId !== software.softwareId);
                                            tempProg.push(program);

                                            setSoftwaresSelected([...softwaresSelected, software]);
                                            setProgramsSelected(tempProg);
                                        }}>
                                            <ListItemIcon>
                                                <Checkbox checked={programsSelected.includes(program)} />
                                            </ListItemIcon>
                                            <ListItemText primary={program.version} />
                                        </ListItemButton>
                                    )
                                }
                            </Collapse>
                        </div>
                    )
                }

            </List>
        </Paper>
    )
}

const confirmList = (array, [softwaresDeselected, setSoftwaresDeselected]) => {
    return (
        <Paper sx={{ width: 200, height: 400, overflow: 'auto' }}>
            <List dense component="div" role="list">
                {
                    array.map(item => 
                        <ListItemButton key={item.softwareId} onClick={() => {
                            if (!softwaresDeselected.includes(item)) {
                                setSoftwaresDeselected([...softwaresDeselected, item]);
                            } else {
                                setSoftwaresDeselected(softwaresDeselected.filter(a => a.softwareId !== item.softwareId));
                            }
                        }}>
                            <ListItemIcon>
                                <Checkbox checked={softwaresDeselected.includes(item)}/>
                            </ListItemIcon>
                            <ListItemText />
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
    const [rightSide, setRightSide] = useState([]);
    const [open, setOpen] = useState([]);
    const [checked, setChecked] = useState({});
    const [softwaresSelected, setSoftwaresSelected] = useState([]);
    const [programsSelected, setProgramsSelected] = useState([]);
    const [softwaresDeselected, setSoftwaresDeselected] = useState([]);

    useEffect(() => {
        getSoftwares(setSoftwares);
        getPrograms(setPrograms);
    }, []);

    useEffect(() => {
        setLeftSide(softwares);
    }, [softwares]);

    const handleLeft = () => {
        var tempLeft = leftSide;
        var tempRight = rightSide;

    }

    const handleRight = () => {

    }

    const programList = () => {

        return (
            <Grid container spacing={2} justifyContent="center" alignItems="center">
                {leftSide.length != 0 && <Grid item>{customList(leftSide, [open, setOpen], [softwaresSelected, setSoftwaresSelected], [programsSelected, setProgramsSelected])}</Grid>}
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
                        <Button
                            sx={{ my: 0.5 }}
                            variant="outlined"
                            size="small"
                            aria-label="move selected left"
                            onClick={() => {
                                console.log(programsSelected);
                            }}
                        >
                            Test
                        </Button>
                    </Grid>
                </Grid>
                <Grid item>{confirmList(rightSide, [softwaresDeselected, setSoftwaresDeselected])}</Grid>
            </Grid>
        )
    }

    return (
        <>
            {softwares.length != 0 && programList()}
        </>

    )
}

export default ProgramTransferList;