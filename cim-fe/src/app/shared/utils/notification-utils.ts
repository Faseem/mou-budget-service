import {Injectable} from "@angular/core";

declare let swal;
declare var $: any;

@Injectable()
export class NotificationUtils {
  public showSadMessage(message?: string) {
    swal({
      title: 'Oops !',
      text: message,
      imageUrl: 'assets/img/sad.gif',
      imageWidth: 110,
      imageHeight: 100,
      animation: false,
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public showErrorMessage(message?: string) {
    swal({
      title: 'Error',
      text: message,
      type: 'error',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public showSuccessMessage(message?: string) {
    swal({
      title: 'Success',
      text: message,
      type: 'success',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public showWarningMessage(message?: string) {
    swal({
      title: 'Warning',
      text: message,
      type: 'warning',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public showWarningMessageWithCallback(callback, message?: string) {
    swal({
      title: 'Warning',
      text: message,
      type: 'warning',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
        this.callback();
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public showInfoMessage(message?: string) {
    swal({
      title: 'Info',
      text: message,
      type: 'info',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  public promptConfirmation(message?: string) {
    return swal({
      title: 'Are you sure?',
      text: message,
      type: 'warning',
      background: '#f9fbff',
      showCancelButton: true,
      confirmButtonColor: '#3085d6',
      cancelButtonColor: '#d33',
      confirmButtonText: 'Yes'
    });
  }

  public showInfoHtmlMessage(message?: string) {
    swal({
      title: 'Info',
      html: message,
      type: 'info',
      // timer: 4000,
      background: '#f9fbff'
    }).then(
      function () {
      },
      // handling the promise rejection
      function (dismiss) {
        if (dismiss === 'timer') {
        }
      })
  }

  showMainLoading() {
    $("#loading-layer-custom").show();
  }

  hideMainLoading() {
    $("#loading-layer-custom").hide();
  }

  showRightSideNotification(message: string, type: string) {
    $('.top-right').notify({
      message: {text: message},
      animate: {
        enter: 'animated fadeInDown',
        exit: 'animated fadeOutUp'
      },
      transition: 'fadeIn',
      type: type,
      fadeOut: {
        enabled: true,
        delay: 10000
      }
    }).show(); // for the ones that aren't closable and don't fade out there is a .hide() function.
  }

}
