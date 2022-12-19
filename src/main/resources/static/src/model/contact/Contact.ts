/* James G Willmore - LJ Computing - (C) 2022 */
class Contact {
    id?: string;
    givenName?: string;
    middleName?: string;
    surname?: string;
    suffix?: string;

    constructor(
        givenName: string,
        middleName: string,
        surname: string,
        suffix: string,
        id?: string
    ) {
        this.givenName = givenName;
        this.middleName = middleName;
        this.surname = surname;
        this.suffix = suffix;
        this.id = id;
    }
}

export default Contact;
