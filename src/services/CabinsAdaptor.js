import {Cabin} from "@/models/cabin.js";

export class CabinsAdaptor {
    resourcesUrl;   // the URL of the cabins resource endpoint
    constructor(resourcesUrl) {
        this.resourcesUrl = resourcesUrl;
    }

    async fetchJson(url, options = null) {
        let response = await fetch(url, options)
        if (response.ok) {
            return await response.json();
        } else {
            return null;
        }
    }

    async asyncFindAll() /* :Promise<Cabin[]> */ {
        const cabinData = await this.fetchJson(this.resourcesUrl);
        return cabinData?.map(Cabin.copyConstructor);
    }

    // those will be added later and be merged into the fetch url.
    async asyncFindById(id) /* :Promise<Cabin> */ {

        const cabinData = await this.fetchJson(this.resourcesUrl + "/" + id)
        return Cabin.copyConstructor(cabinData);
    }

    async asyncSave(cabin) /* :Promise<Cabin> */ {
        let response;
        if (cabin.id === 0 || cabin.id === undefined || cabin.id === null) {
            response = await this.fetchJson(`${this.resourcesUrl}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cabin),
            });
        } else {
            response = await this.fetchJson(`${this.resourcesUrl}/${cabin.id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(cabin)
            });
        }
        return response;
    }

    async asyncDeleteById(id) /* :Promise<void> */ {
        return await this.fetchJson(`${this.resourcesUrl}/${id}`, {
           method: 'DELETE',
           headers: {
               'Content-Type': 'application/json'
           },
       });
    }
}
