/* James G Willmore - LJ Computing - (C) 2022 */
import axios from "axios";

const instance = axios.create({
    baseURL: "/api/",
    timeout: 1000,
});

export default instance;
