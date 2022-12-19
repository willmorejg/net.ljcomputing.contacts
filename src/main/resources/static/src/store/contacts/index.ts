/* James G Willmore - LJ Computing - (C) 2022 */
import PageRequest from "@/model/shared/PageRequest";
import PageResponse from "@/model/shared/PageResponse";
import { Module } from "vuex";

const contactsModule: Module<any, any> = {
    state: {
        contactsPageRequest: new PageRequest(0, 2),
        contactsPageResponse: new PageResponse(),
        contacts: [],
    },

    getters: {
        contactsPageRequest: (state) => state.contactsPageRequest,
        contactsPageResponse: (state) => state.contactsPageResponse,
        contacts: (state) => state.contacts,
    },

    mutations: {
        CONTACTS_PAGE_REQUEST(state, pageRequest) {
            state.contactsPageRequest = pageRequest;
        },
        CONTACTS_PAGE_RESPONSE(state, pageResponse) {
            state.contactsPageResponse = pageResponse;
        },
        CONTACTS(state, contacts) {
            state.contacts = contacts;
        },
    },

    actions: {
        contactsPageRequest(context, pageRequest) {
            console.log("CONTACTS_PAGE_REQUEST");
            context.commit("CONTACTS_PAGE_REQUEST", pageRequest);
        },
        contactsPageResponse(context, pageResponse) {
            console.log("CONTACTS_PAGE_RESPONSE");
            context.commit("CONTACTS_PAGE_RESPONSE", pageResponse);
        },
        contacts(context, contacts) {
            console.log("CONTACTS");
            context.commit("CONTACTS", contacts);
        },
    },
};

export default contactsModule;
