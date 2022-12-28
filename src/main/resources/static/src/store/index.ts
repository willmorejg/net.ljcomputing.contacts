/* James G Willmore - LJ Computing - (C) 2022 */
import contactsStore from "@/store/contacts/index";
import { createStore } from "vuex";

export default createStore({
    state: {
        reloadContactsList: false,
        retrieveContact: false,
        errorMessage: null,
    },
    getters: {
        isReload: (state) => state.reloadContactsList,
        isRetrieve: (state) => state.retrieveContact,
        loading: (state) => state.reloadContactsList || state.retrieveContact,
        errorMessage: (state) => state.errorMessage,
    },
    mutations: {
        RELOAD(state) {
            state.reloadContactsList = true;
        },
        RELOADED(state) {
            state.reloadContactsList = false;
        },
        RETRIEVE(state) {
            state.retrieveContact = true;
        },
        RETRIEVED(state) {
            state.retrieveContact = false;
        },
    },
    actions: {
        reload(context) {
            console.log("RELOAD");
            context.commit("RELOAD");
        },
        reloaded(context) {
            console.log("RELOADED");
            context.commit("RELOADED");
        },
        retrieve(context) {
            console.log("RETRIEVE");
            context.commit("RETRIEVE");
        },
        retrieved(context) {
            console.log("RETRIEVED");
            context.commit("CONTACT_ID", "");
            context.commit("RETRIEVED");
        },
    },
    modules: {
        contactsStore,
    },
});
