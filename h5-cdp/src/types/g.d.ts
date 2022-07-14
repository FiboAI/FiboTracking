type FromRules = Record<string, FieldRule[]>;

type FormValidateErr = undefined | FromRules;

interface FormValidate {
    (err: FormValidateErr): void
}

interface MResponse<T> {
    code: string,
    msg: string,
    datas?: T
};