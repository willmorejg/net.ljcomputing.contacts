/* James G Willmore - LJ Computing - (C) 2022 */
import axios from "axios";

const instance = axios.create({
    baseURL: "http://localhost:9987",
    timeout: 1000,
});

export default instance;
