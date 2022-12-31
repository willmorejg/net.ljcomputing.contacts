/* James G Willmore - LJ Computing - (C) 2022 */
class PageRequest {
    page?: number = 0;
    itemsPerPage?: number;
    direction?: string;
    field?: string;

    constructor(
        page?: number,
        itemsPerPage?: number,
        direction?: string,
        field?: string
    ) {
        this.page = page;
        this.itemsPerPage = itemsPerPage;
        this.direction = direction;
        this.field = field;
    }
}

export default PageRequest;
