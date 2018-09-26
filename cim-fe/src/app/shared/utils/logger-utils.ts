import {Observable} from "rxjs/Observable";
import {environment} from "../../../environments/environment";

/**
 * EnableLogging Decorator
 * =======================
 * Logs function name with parameters and response (if exists) in console. For enable logging refer below example.
 * Works when enable Dev mode.
 * Example:-
 *            sampleFunction(param1: number, param2: number) {
 *              return param1 + param2;
 *            }
 *
 * @param {Object} target
 * @param {string} propertyKey
 * @param {TypedPropertyDescriptor<any>} descriptor
 * @returns {TypedPropertyDescriptor<any>}
 * @constructor
 */
export const EnableLogging = (target: Object, propertyKey: string, descriptor: TypedPropertyDescriptor<any>) => {
  if (!environment.production) {
    let originalMethod = descriptor.value;
    descriptor.value = function (...args: any[]) {
      console.log(
        '%c' + new Date().toISOString() +
        '%c\t--\t CALLING ~ %c' + propertyKey +
        '%c\t\t PARAMS ~ %c' + JSON.stringify(args),
        'color:blue;',
        'color:#7E1F3C;',
        'color:#8E1CD7;',
        'color:#7E1F3C;',
        'color:#8E1CD7;');
      let result = originalMethod.apply(this, args);
      if (result) {
        if (result instanceof Observable) {
          result.subscribe(
            res => {
              console.log(
                '%c' + new Date().toISOString() +
                '%c\t--\t RESPONSE FOR %c' + propertyKey +
                '\n\n' + JSON.stringify(res, null, "\t"),
                'color:blue;',
                'color:#7E1F3C;',
                'color:#8E1CD7;');
            },
            error => {
              console.log(
                '%c' + new Date().toISOString() +
                '%c\t--\t ERROR OCCURRED FOR %c' + propertyKey +
                '\n\n' + error.message,
                'color:blue;',
                'color:red',
                'color:#7E1F3C;');
            });
        } else {
          console.log(result);
        }
      }
      return result;
    };
    return descriptor;
  }
};
