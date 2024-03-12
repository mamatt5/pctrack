import Autocomplete from "@mui/material/Autocomplete";
import FormControl from "@mui/material/FormControl";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import Select from "@mui/material/Select";
import Stack from "@mui/material/Stack";
import TextField from "@mui/material/TextField";
import * as React from "react";

const ITEM_HEIGHT = 48;
const ITEM_PADDING_TOP = 8;
const MenuProps = {
	PaperProps: {
		style: {
			maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
			width: 250,
		},
	},
};

export default function SelectSmall({ array, label, item, setItem, disabledKey, setChange }) {

	return (
		<FormControl sx={{ minWidth: "100%" }} component="div">
			<InputLabel id="select">Permissions</InputLabel>
			<Select
				labelId="demo-select-small-label"
				id="select"
				value={item}
				label="Permissions"
				onChange={(e) => {
					setItem(e.target.value);
					setChange(c => !c);
				}}
			>
				{array.map((name) =>
					name[label] === "" ? (
						<MenuItem key="none" value="none" disabled={name[label] === disabledKey ? true : false}>
							<em>None</em>
						</MenuItem>
					) : (
						<MenuItem key={name[label]} value={name[label]} disabled={name[label] === disabledKey ? true : false}>
							{name[label]}
						</MenuItem>
					)
				)}
			</Select>
		</FormControl>
	);
}

//
export function MultipleSelect(arrayOfObjects, key, label, placeholder, canEdit, setSelects, defaultValues) {

	// freaking objects arent equal
	//https://stackoverflow.com/questions/61947941/material-ui-autocomplete-warning-the-value-provided-to-autocomplete-is-invalid
	const customOptionIsEqual = (option, value) => {
	  // Customize the equality check here
	  return option[key] === value[key];
	};

	return (
	  <Stack spacing={3}>
		<Autocomplete
		  isOptionEqualToValue={customOptionIsEqual}
		  sx={
			!canEdit
			  ? {
				  "& .MuiOutlinedInput-root .MuiOutlinedInput-notchedOutline": {
					border: "1px solid #eee",
				  },
				}
			  : {}
		  }
		  readOnly={!canEdit}
		  onChange={(e, value) => setSelects(value)}
		  defaultValue={canEdit ? defaultValues : arrayOfObjects}
		  multiple
		  disabled={arrayOfObjects.length === 0 ? true : false}
		  id="tags-outlined"
		  options={arrayOfObjects}
		  getOptionLabel={(option) => option[key]}
		  filterSelectedOptions
		  renderInput={(params) => <TextField {...params} label={label} placeholder={placeholder} />}
		/>
	  </Stack>
	);
  }