export class SessionSbService {
    BROWSER_STORAGE_ITEM_NAME;
    RESOURCES_URL;
    _currentToken;
    _currentAccount;

    constructor(resourcesUrl, browserStorageItemName) {
        this.BROWSER_STORAGE_ITEM_NAME = browserStorageItemName;
        this.RESOURCES_URL = resourcesUrl;
        this._currentToken = null;
        this._currentAccount = null;
        this.getTokenFromBrowserStorage();
    }

    getTokenFromBrowserStorage() {
        if (this._currentToken != null) return this._currentToken
        return null;
    }

    saveTokenIntoBrowserStorage(token, account) {
        this._currentToken = token;
        if (token == null) {
            this._currentAccount = null;
            window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME);
            window.sessionStorage.removeItem(this.BROWSER_STORAGE_ITEM_NAME + "_ACC");
            window.sessionStorage.removeItem(account)
        } else {
            this._currentAccount = account;
            console.log("New token for " + account.name + ": " + token);
            window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME, token);
            window.sessionStorage.setItem(this.BROWSER_STORAGE_ITEM_NAME + "_ACC", JSON.stringify(account));
        }
    }

    async asyncSignIn(email, password) {
        // eslint-disable-next-line no-debugger
        // debugger
        const body = JSON.stringify({email: email, password: password});
        let response = await fetch(this.RESOURCES_URL + "/login",
            {
                method: 'POST',
                headers: {'Content-Type': 'application/json'},
                body: body,
                credentials: 'include'
            })
        console.log(response)
        if (response.ok) {
            let account = await response.json();
            this.saveTokenIntoBrowserStorage(
                response.headers.get('Authorization'),
                account);
            console.log(account);
            return account;
        } else {
            console.log(response)
            return null;
        }
    }

    isAuthenticated() {
        return this.getTokenFromBrowserStorage() !== null && this._currentAccount !== null;
    }

    signOut() {
        this.saveTokenIntoBrowserStorage(null, null);
        this._currentAccount = null;
    }

    get currentToken() {
        return this._currentToken;
    }

    get currentAccount() {
        return this._currentAccount;
    }
}
