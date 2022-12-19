/* James G Willmore - LJ Computing - (C) 2022 */
import Contact from "@/model/contact/Contact";
import PageRequest from "@/model/shared/PageRequest";
import PageResponse from "@/model/shared/PageResponse";
import contactsStore from "@/store/contacts/index";
import { createStore } from "vuex";

export default createStore({
    state: {
        reloadContactsList: false,
        retrieveContact: false,
        errorMessage: null,
        contactId: "",
    },
    getters: {
        isReload: (state) => state.reloadContactsList,
        isRetrieve: (state) => state.retrieveContact,
        loading: (state) => state.reloadContactsList || state.retrieveContact,
        errorMessage: (state) => state.errorMessage,
        contactId: (state) => state.contactId,
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
        CONTACT_ID(state, id) {
            state.contactId = id;
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
        contactId(context, id) {
            console.log("CONTACT_ID");
            context.commit("CONTACT_ID", id);
        },
    },
    modules: {
        contactsStore,
    },
});
