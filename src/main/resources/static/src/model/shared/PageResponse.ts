/* James G Willmore - LJ Computing - (C) 2022 */
class PageResponse {
    data?: Array<any> = [];
    totalElements?: number = 0;
    errorMessage?: string = "";

    constructor(
        data?: Array<any>,
        totalElements?: number,
        errorMessage?: string
    ) {
        this.data = data;
        this.totalElements = totalElements;
        this.errorMessage = errorMessage;
    }

    public hasError(): boolean {
        return this.errorMessage !== "";
    }
}

export default PageResponse;
