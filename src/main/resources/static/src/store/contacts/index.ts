/* James G Willmore - LJ Computing - (C) 2022 */
import Contact from "@/model/contact/Contact";
import PageRequest from "@/model/shared/PageRequest";
import PageResponse from "@/model/shared/PageResponse";
import { Module } from "vuex";

const contactsModule: Module<any, any> = {
    state: {
        contactsPageRequest: new PageRequest(0, 5, "ASC", "surname"),
        contactsPageResponse: new PageResponse(),
        contacts: [],
        contactId: "",
        contact: new Contact("", "", "", ""),
    },

    getters: {
        contactsPageRequest: (state) => state.contactsPageRequest,
        contactsPageResponse: (state) => state.contactsPageResponse,
        contacts: (state) => state.contacts,
        contactId: (state) => state.contactId,
        contact: (state) => state.contact,
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
        CONTACT_ID(state, id) {
            state.contactId = id;
        },
        CONTACT(state, contact) {
            state.contact = contact;
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
        contactId(context, id) {
            console.log("CONTACT_ID");
            context.commit("CONTACT_ID", id);
        },
        contact(context, contact) {
            console.log("CONTACT");
            context.commit("CONTACT", contact);
        },
    },
};

export default contactsModule;
