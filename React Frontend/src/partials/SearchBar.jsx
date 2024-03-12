import SearchIcon from "@mui/icons-material/Search";
import FormControl from "@mui/material/FormControl";
import InputAdornment from "@mui/material/InputAdornment";
import OutlinedInput from "@mui/material/OutlinedInput";

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
