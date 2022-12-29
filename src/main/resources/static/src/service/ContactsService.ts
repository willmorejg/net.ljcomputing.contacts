/* James G Willmore - LJ Computing - (C) 2022 */
import store from "@/store";
import axios from "@/axios-instance";
import PageRequest from "@/model/shared/PageRequest";
import PageResponse from "@/model/shared/PageResponse";
import Contact from "@/model/contact/Contact";

export default class ContactsServices {
    public static setActivePage(page: number): void {
        let pageRequest: PageRequest = store.getters
            .contactsPageRequest as PageRequest;

        pageRequest.page = page;
        store.dispatch("contactsPageRequest", pageRequest);
    }

    public static async loadContacts(): Promise<void> {
        store.dispatch("reload");
        let pageRequest: PageRequest = store.getters
            .contactsPageRequest as PageRequest;

        console.log("pageRequest", pageRequest);
        store.dispatch("contactsPageRequest", pageRequest);

        await axios
            .get("api/contacts", {
                params: {
                    page: pageRequest.page,
                    pageSize: pageRequest.itemsPerPage,
                },
            })
            .then(function (response) {
                console.log("response", response);
                let pageResponse: PageResponse = new PageResponse(
                    response.data.content as Array<Contact>,
                    response.data.totalElements,
                    ""
                );
                console.log("pageResponse", pageResponse);
                store.dispatch("contactsPageResponse", pageResponse);
                store.dispatch("reloaded");
            })
            .catch(function (error) {
                console.log("error", error);
                let pageResponse: PageResponse = new PageResponse(
                    [],
                    0,
                    error.message
                );
                store.dispatch("contactsPageResponse", pageResponse);
                store.dispatch("reloaded");
            });
    }

    public static async deleteContact(id: string): Promise<void> {
        await axios
            .delete("api/contacts/" + id)
            .then(function (response) {
                console.log("response", response);
                store.dispatch("reload");
            })
            .catch(function (error) {
                console.log("error", error);
                let pageResponse: PageResponse = new PageResponse(
                    error.message
                );
                store.dispatch("contactsPageResponse", pageResponse);
                store.dispatch("reload");
            });
    }

    public static async loadContact(id: string): Promise<void> {
        await axios
            .get("api/contacts/" + id)
            .then(function (response) {
                let contact = new Contact(
                    response.data.givenName,
                    response.data.middleName,
                    response.data.surname,
                    response.data.suffix,
                    response.data.id
                );
                store.dispatch("contact", contact);
                store.dispatch("retrieved");
            })
            .catch(function (error) {
                console.log("error", error);
                let pageResponse: PageResponse = new PageResponse(
                    error.message
                );
                store.dispatch("contactsPageResponse", pageResponse);
                store.dispatch("retrieved");
            });
    }

    public static async persistContact(contact: Contact): Promise<void> {
        await axios
            .post("api/contacts", contact)
            .then(function (response) {
                store.dispatch("retrieved");
            })
            .catch(function (error) {
                console.log("error", error);
                let pageResponse: PageResponse = new PageResponse(
                    error.message
                );
                store.dispatch("contactsPageResponse", pageResponse);
                store.dispatch("retrieved");
            });
    }
}
