import * as React from "react";
import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import MenuItem from "@mui/material/MenuItem";
import FormControl from "@mui/material/FormControl";
import ListItemText from "@mui/material/ListItemText";
import Select from "@mui/material/Select";
import Checkbox from "@mui/material/Checkbox";
import { Label } from "@mui/icons-material";

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

export default function SelectSmall({ array, label }) {
	console.log(array);
	const [item, setItem] = React.useState("");

	const handleChange = (event) => {
		setItem(event.target.value);
	};

	return (
		<FormControl sx={{ m: 1, minWidth: 160 }} size="small">
			<InputLabel id="demo-select-small-label">Permissions</InputLabel>
			<Select
				labelId="demo-select-small-label"
				id="demo-select-small"
				value={item}
				label="Permissions"
				onChange={handleChange}
			>
				<MenuItem value="">
					<em>None</em>
				</MenuItem>
				{array.map((name) => (
					<MenuItem key={name[label]} value={name[label]}>
						{name[label]}
					</MenuItem>
				))}
			</Select>
		</FormControl>
	);
}

export function MultipleSelectCheckmarks({ array, label }) {
	const [arrItem, setItem] = React.useState([]);

	const handleChange = (event) => {
		const {
			target: { value },
		} = event;
		setItem(
			// On autofill we get a stringified value.
			typeof value === "string" ? value.split(",") : value
		);
	};

	return (
		<div>
			<FormControl sx={{ m: 1, width: 300 }}>
				<InputLabel id="demo-multiple-checkbox-label">{label}</InputLabel>
				<Select
					labelId="multiple-checkbox"
					id="multiple-checkbox"
					multiple
					value={arrItem}
					onChange={handleChange}
					input={<OutlinedInput label={label} />}
					renderValue={(selected) => selected.join(", ")}
					MenuProps={MenuProps}
				>
					{array.map((item) => (
						<MenuItem key={item} value={item}>
							<Checkbox checked={arrItem.indexOf(item) > -1} />
							<ListItemText primary={item} />
						</MenuItem>
					))}
				</Select>
			</FormControl>
		</div>
	);
}
