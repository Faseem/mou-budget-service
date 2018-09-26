import {Injectable} from "@angular/core";
import {NotificationUtils} from "../../shared/utils/notification-utils";
import {Validator} from "../../shared/utils/validation-utils";

@Injectable()
export class ErrorHandler {

  constructor(private notificationUtils: NotificationUtils,
              private validator: Validator) { }

  handle(error: any) {
    console.log(error)
    if (this.validator.isNotNull(error) && this.validator.isNotNull(error.status) && error.status == 404) {
      this.notificationUtils.showInfoMessage(`Cannot find data.`);
    }

    if (this.validator.isNotNull(error) && this.validator.isNotNull(error.status) && error.status == 204) {
      this.notificationUtils.showInfoMessage(error.message);
      return;
    }

    if (this.validator.isNotNull(error) && this.validator.isNotNull(error.message)) {
      if (error.toString().indexOf('Unexpected end of JSON input') != -1) {
        this.notificationUtils.showInfoMessage('Cannot find data.');
      } else if (error.message && (error.message.indexOf('No profiles available') > -1)) {
        this.notificationUtils.showErrorMessage('No Profiles Are Available For  Searched Criteria');
      }
      else {
        this.notificationUtils.showErrorMessage(error.message);
      }
    } else {
      this.notificationUtils.showErrorMessage('Error occurred.');
    }
  }
}
