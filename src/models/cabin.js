// noinspection JSAnnotator

export class Cabin {
    id;
    type;
    location;
    description;
    image;
    pricePerWeek;
    numAvailable;

    constructor(id, type, location, description, image, pricePerWeek, numAvailable) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.description = description;
        this.image = image;
        this.pricePerWeek = pricePerWeek;
        this.numAvailable = numAvailable;
    }

    static createSampleCabin(pId = 0) {

        const id = pId;
        const type = randomType();
        const location = randomLocation();

        let description = descriptionGenerator(type);

        if (type === "BeachGear") {
            description = null;
        }

        const image = randomImage();
        const price = Math.round(Math.random() * (2500 - 100) + 100);
        const available = Math.round(Math.random() * (50 - 1) + 1);

        return new Cabin(id, type, location, description, image, price, available)
    }

    static copyConstructor(cabin) {
        if (cabin == null) return null;
        return Object.assign(new Cabin(0), cabin);
    }
}

Cabin.CabinType = {
    BeachGear: "BeachGear",
    SmallDayTime: "SmallDayTime",
    SmallLodge: "SmallLodge",
    LargeLodge: "LargeLodge",
    FamilyLodge: "FamilyLodge"
};

function randomType() {
    const number = Math.round(Math.random() * (5 - 1) + 1);

    if (number === 1) {
        return Cabin.CabinType.BeachGear;
    } else if (number === 2) {
        return Cabin.CabinType.SmallDayTime;
    } else if (number === 3) {
        return Cabin.CabinType.SmallLodge;
    } else if (number === 4) {
        return Cabin.CabinType.LargeLodge;
    } else {
        return Cabin.CabinType.FamilyLodge;
    }
}

Cabin.Location =
    ["Turquoise Coast, Turkey",
        "Big Sur, California",
        "Antrim Coast, Northern Ireland",
        "Malabar Coast, India",
        "Skeleton Coast, Namibia",
        "Garden Route, South Africa",
        "Great Ocean Road, Australia",
        "Nā Pali Coast, Hawaii",
        "Riviera Maya, Mexico",
        "Dalmatian Coast, Croatia",
        "Raja Ampat, Indonesia"];

function randomLocation() {
    return Cabin.Location[Math.round(Math.random() * (10))];
}

function descriptionGenerator(type) {
    const word = ["colourful", "modern", "spacy", "comfortable", "white", "cosy", "characteristics"]

    return word[Math.round(Math.random() * (6))] + " " + type + " model-" + Math.round(Math.random() * (5 - 1) + 1);
}

Cabin.Images =
    ["https://www.novascotia.com/sites/default/files/inline-images/ARGYLER%20LODGE%201920x1080.jpg",
        "https://www.surfenbeachstrandhuisjes.nl/uploads/images/820x530/IMG_6043.jpg",
        "https://w0.peakpx.com/wallpaper/700/114/HD-wallpaper-cabin-in-the-tropics-ocean-tropics-sky-cabin.jpg",
        "https://www.pixelstalk.net/wp-content/uploads/2016/08/Log-Cabin-Desktop-Wallpaper.jpeg",
        "https://www.kiamacoast.com.au/surf-beach-holiday-park/wp-content/uploads/2017/02/NEGphotography_Surf-Beach-Cabin-25-Admiral_7.jpg"];

function randomImage() {
    return Cabin.Images[Math.round(Math.random() * (4))];
}
