/* James G Willmore - LJ Computing - (C) 2023 */
import Model from "./Model";
interface Contact extends Model {
    givenName?: string;
    middleName?: string;
    surname?: string;
    suffix?: string;
}

export default Contact;
