/* James G Willmore - LJ Computing - (C) 2022 */
class PageRequest {
    page?: number = 0;
    itemsPerPage?: number = 2;

    constructor(page?: number, itemsPerPage?: number) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
    }
}

export default PageRequest;