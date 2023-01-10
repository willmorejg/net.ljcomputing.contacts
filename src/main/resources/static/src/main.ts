/* James G Willmore - LJ Computing - (C) 2022 */
import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";

import "bootstrap";
import "bootswatch/dist/cosmo/bootstrap.min.css";

import { library } from "@fortawesome/fontawesome-svg-core";
import {
    faSort,
    faSortUp,
    faSortDown,
} from "@fortawesome/free-solid-svg-icons";

library.add(faSort);
library.add(faSortUp);
library.add(faSortDown);

import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

createApp(App)
    .use(store)
    .use(router)
    .component("font-awesome-icon", FontAwesomeIcon)
    .mount("#app");
