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
        // contactsPageRequest: new PageRequest(0, 2),
        // contactsPageResponse: new PageResponse(),
        errorMessage: null,
        contactId: "",
        // contacts: null,
    },
    getters: {
        isReload: (state) => state.reloadContactsList,
        isRetrieve: (state) => state.retrieveContact,
        loading: (state) => state.reloadContactsList || state.retrieveContact,
        // contactsPageRequest: (state) => state.contactsPageRequest,
        // contactsPageResponse: (state) => state.contactsPageResponse,
        errorMessage: (state) => state.errorMessage,
        contactId: (state) => state.contactId,
        // contacts: (state) => state.contacts,
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
        // CONTACTS_PAGE_REQUEST(state, pageRequest) {
        //     state.contactsPageRequest = pageRequest;
        // },
        // CONTACTS_PAGE_RESPONSE(state, pageResponse) {
        //     state.contactsPageResponse = pageResponse;
        // },
        CONTACT_ID(state, id) {
            state.contactId = id;
        },
        // CONTACTS(state, contacts) {
        //     state.contacts = contacts;
        // },
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
        // contactsPageRequest(context, pageRequest) {
        //     console.log("CONTACTS_PAGE_REQUEST");
        //     context.commit("CONTACTS_PAGE_REQUEST", pageRequest);
        // },
        // contactsPageResponse(context, pageResponse) {
        //     console.log("CONTACTS_PAGE_RESPONSE");
        //     context.commit("CONTACTS_PAGE_RESPONSE", pageResponse);
        // },
        contactId(context, id) {
            console.log("CONTACT_ID");
            context.commit("CONTACT_ID", id);
        },
        // contacts(context, contacts) {
        //     console.log("CONTACTS");
        //     context.commit("CONTACTS", contacts);
        // },
    },
    modules: {
        contactsStore,
    },
});
