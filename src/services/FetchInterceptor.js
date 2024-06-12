import * as fetchIntercept from "fetch-intercept";

export class FetchInterceptor {
    static theInstance;
    sessionService;
    router;
    unregister;

    constructor(sessionService, router) {
        FetchInterceptor.theInstance = this;
        this.sessionService = sessionService;
        this.router = router;
        this.unregister = fetchIntercept.register(this)
    }

    request(url, options) {
        let token = FetchInterceptor.theInstance.sessionService.currentToken;

        if (token == null) {
            return [url, options];
        } else if (options == null) {
            return [url, {headers: {Authorization: token}}]
        } else {
            let newOptions = {...options};
            if (newOptions.headers == null) {
                newOptions.headers = {Authorization: token};
            } else {
                newOptions.headers.Authorization = token;
            }
            return [url, newOptions];
        }
    }

    requestError(error) {
        return Promise.reject(error);
    }

    response(response) {
        if (response.status >= 400 && response.status < 600) {
            FetchInterceptor.theInstance.handleErrorInResponse(response);
        }
        return response;

    }

    responseError(error){
        return Promise.reject(error)
    }

    async handleErrorInResponse(response) {
        if (response.status === 401) {
            console.log(response);
            this.router.push("/cabins/SignIn");
        } else if (response.status === 406) {
            let errorData = await response.json();
            let errorMessage = `Request-url = ${response.request.url}`
                + `<br>Response status code = ${response.status}`
                + `<br>Error Message = ${errorData.error}: ${errorData.message}`
            this.router.push({ name: 'RequestError', params: { message: errorMessage}});
        } else {
            console.log(response)
        }
    }

}

