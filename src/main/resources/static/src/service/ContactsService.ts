/* James G Willmore - LJ Computing - (C) 2022 */
import store from "@/store";
import axios from "@/axios-instance";
import PageRequest from "@/model/shared/PageRequest";
import PageResponse from "@/model/shared/PageResponse";
import Contact from "@/model/contact/Contact";

const API_CONTACTS_URL: string = "contacts";

export default class ContactsServices {
    public static setPageRequest(
        page: number,
        field: string,
        direction: string,
        filterField: string,
        filterValue: string
    ): void {
        let pageRequest: PageRequest = store.getters
            .contactsPageRequest as PageRequest;
        pageRequest.page = page;
        pageRequest.field = field;
        pageRequest.direction = direction;
        pageRequest.filterValue = filterValue;

        store.dispatch("contactsPageRequest", pageRequest);
    }

    public static async loadContacts(): Promise<void> {
        store.dispatch("reload");
        let pageRequest: PageRequest = store.getters
            .contactsPageRequest as PageRequest;

        console.log("pageRequest", pageRequest);
        store.dispatch("contactsPageRequest", pageRequest);

        await axios
            .get(API_CONTACTS_URL, {
                params: {
                    page: pageRequest.page,
                    pageSize: pageRequest.itemsPerPage,
                    field: pageRequest.field,
                    direction: pageRequest.direction,
                    filterField: pageRequest.filterField,
                    filterValue: pageRequest.filterValue,
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

                let filteredData: Array<Contact> = [];
                let filterValue = store.getters.filterValue;
                console.log(
                    "filterValue, store.getters.contacts",
                    filterValue,
                    store.getters.contacts
                );

                let responseData: any = pageResponse.data;
                if (responseData.length > 0 && filterValue != "") {
                    responseData.forEach(function (element: Contact) {
                        console.log(
                            "element.surname == filterValue",
                            element.surname,
                            filterValue
                        );
                        if (
                            element.surname
                                ?.toString()
                                .toLowerCase()
                                .includes(filterValue.toLowerCase())
                        ) {
                            filteredData.push(element);
                        }
                    });

                    pageResponse.data = filteredData;
                    pageResponse.totalElements = filteredData.length;
                }

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
            .delete(API_CONTACTS_URL + "/" + id)
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
            .get(API_CONTACTS_URL + "/" + id)
            .then(function (response) {
                let contact = {
                    givenName: response.data.givenName,
                    middleName: response.data.middleName,
                    surname: response.data.surname,
                    suffix: response.data.suffix,
                    id: response.data.id,
                };
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
            .post(API_CONTACTS_URL, contact)
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
