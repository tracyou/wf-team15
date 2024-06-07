import * as fetchIntercept from "fetch-intercept";

export class FetchInterceptor {
    static theInstance;
    sessionService;
    router;
    unregister;

    constructor(session, router) {
        this.sessionService = session;
        this.router = router;

        FetchInterceptor.theInstance = this;
        this.unregister = fetchIntercept.register(this);
        console.log("FetchInterceptor has been registered current token = ",
            FetchInterceptor.theInstance.sessionService._currentToken);
    }

    request(url, options) {
        // let token = FetchInterceptor.theInstance.sessionService._currentToken;
        //
        // if (token == null) {
        //     return [url, options];
        // } else if (options == null) {
        //     return [url, {headers: {Authorization: token}}]
        // } else {
        //     let newOptions = {...options};
        //     if (newOptions.headers == null) {
        //         newOptions.headers = {Authorization: token};
        //     } else {
        //         newOptions.headers.Authorization = token;
        //     }
        //     return [url, newOptions]
        // }
    }

    requestError(error) {
    }

    response(response) {
    }

    responseError(error) {
    }
}
