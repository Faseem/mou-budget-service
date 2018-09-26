import {Injectable} from "@angular/core";

declare let swal;
declare var $: any;

@Injectable()
export class ElementEventsUtils {

  public focusToElement(elementID?: string) {
    setTimeout(function () {
      $("#" + elementID).focus()
    }, 500);
  }

  public clickElement(elementID?: string) {
    $("#" + elementID).click();
  }
}
