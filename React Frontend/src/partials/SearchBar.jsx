import OutlinedInput from "@mui/material/OutlinedInput";
import InputLabel from "@mui/material/InputLabel";
import InputAdornment from "@mui/material/InputAdornment";
import FormControl from "@mui/material/FormControl";
import SearchIcon from "@mui/icons-material/Search";

const SearchBar = ({ placeholder, setQuery }) => {
	return (
		<>
			<FormControl sx={{ m: 1 }}>
				<OutlinedInput
					id="search"
					size="small"
					placeholder={placeholder}
					startAdornment={
						<InputAdornment position="start">
							<SearchIcon />
						</InputAdornment>
					}
					sx={{ borderRadius: 5 }}
                    onChange={(e) => {setQuery(e.target.value)}}
				/>
			</FormControl>

		</>
	);
};

export default SearchBar;
