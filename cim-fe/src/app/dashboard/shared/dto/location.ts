import {Expose, serialize} from "class-transformer";

export class Location {

  @Expose({name: 'locationCode'})
  locationCode: string;

  @Expose({name: 'locationDescription'})
  locationDescription: string;

  @Expose({name: 'locationName'})
  locationName: string;

  constructor() {
  }

  setLocationCode(value: string): Location {
    this.locationCode = value;
    return this;
  }

  setLocationDescription(value: string): Location {
    this.locationDescription = value;
    return this;
  }

  setLocationName(value: string): Location {
    this.locationName = value;
    return this;
  }

  toJSon(): any {
    return serialize(this);
  }
}
