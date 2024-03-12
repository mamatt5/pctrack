import axios from 'axios';
import Config from "../configs.json"

const PORT = Config.PORT
const NAME = Config.NAME

// CONFIGS (options param as an object)
// method,
// endpoint,
// data = {},
// params = {},
// auth = {},
// header = {}

/**
 *
 * @param {*} onSuccessDo the function that runs if call is successful
 * @param {*} onFailureDo the function that runs if call is unsuccessful
 * @param {*} options any configs required, only method and endpoint are mandatory for all calls
 * @param  {...any} optional any parameters that need to be passed into onSuccessDo
 */
const callApi = (onSuccessDo, onFailureDo, options, ...optional) => {

    const { method, endpoint, data = {}, params = {}, auth = {}, header = {} } = options;
    const URL = `http://localhost:${PORT}/${NAME}/${endpoint}`

    const configs = {
        method: method,
        url: URL,
        data: data,
        params: params,
        headers: header,
    }

    // if an auth if provided, then add it to configs.
    // leaving auth = {} in the configs will NOT work as it will still require authentication
    if (Object.keys(auth).length !== 0) {
        configs.auth = auth;
    }

    // if the user is logged in, allow authorization. a user is logged in
    // if their token is stored in local storage
    if (localStorage.getItem('token')) {
        configs.headers =  {
            Authorization:
            `${localStorage.getItem('token')}`
        }
    }

    axios(configs)
        .then(response => {

            onSuccessDo(response.data, ...optional);
        })
        .catch(error => {

            onFailureDo(error)
        })


}

export default callApi