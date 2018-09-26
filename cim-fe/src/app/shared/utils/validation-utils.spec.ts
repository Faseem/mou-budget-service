import {async} from "@angular/core/testing";
import {Validator} from "./validation-utils";

describe('Validator', () => {
  let validator = new Validator();

  beforeEach(() => {

  });

  it(`test is number {} value = 1`, async(() => {
    const res = validator.isNumber(1);
    expect(res).toBeTruthy();
  }));

  it(`test is number {} value = 'a'`, async(() => {
    const res = validator.isNumber('a');
    console.log(res);
    expect(res).toBeFalsy();
  }));

  it(`test is number {} value = 1.23`, async(() => {
    const res = validator.isNumber(1.23);
    expect(res).toBeTruthy();
  }));

  it(`test is number {} value = 123`, async(() => {
    let value = null;
    value = 123;
    const res = validator.isNumber(value);
    expect(res).toBeTruthy();
  }));

  it(`test is null {} value = 'a'`, async(() => {
    const res = validator.isNull('a');
    expect(res).toBeFalsy();
  }));

  it(`test is null {} value = null`, async(() => {
    const res = validator.isNull(null);
    expect(res).toBeTruthy();
  }));

  it(`test is null {} value = undefined`, async(() => {
    let value;
    const res = validator.isNull(value);
    expect(res).toBeTruthy();
  }));

  it(`test is not null {} value = 'a'`, async(() => {
    const res = validator.isNotNull('a');
    expect(res).toBeTruthy();
  }));

  it(`test is not null {} value = null`, async(() => {
    const res = validator.isNotNull(null);
    expect(res).toBeFalsy();
  }));

  it(`test is not null {} value = undefined`, async(() => {
    let a;
    const res = validator.isNotNull(a);
    expect(res).toBeFalsy();
  }));

  it(`test has value {} value = undefined`, async(() => {
    let value;
    const res = validator.hasValue(value);
    expect(res).toBeFalsy();
  }));

  it(`test has value {} value = 123`, async(() => {
    const res = validator.hasValue(123);
    expect(res).toBeTruthy();
  }));

  it(`test has value {} value = 1.23`, async(() => {
    const res = validator.hasValue(1.23);
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 199183000996`, async(() => {
    const res = validator.isNIC('199183000996');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 19918300099`, async(() => {
    const res = validator.isNIC('19918300099');
    expect(res).toBeFalsy();
  }));

  it(`test is nic {} value = 772517622V`, async(() => {
    const res = validator.isNIC('772517622V');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622X`, async(() => {
    const res = validator.isNIC('772517622X');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622v`, async(() => {
    const res = validator.isNIC('772517622v');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622x`, async(() => {
    const res = validator.isNIC('772517622x');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 72517622V`, async(() => {
    const res = validator.isNIC('72517622V');
    expect(res).toBeFalsy();
  }));

  it(`test is new nic {} value = 199183000996`, async(() => {
    const res = validator.isNewNIC('199183000996');
    expect(res).toBeTruthy();
  }));

  it(`test is new nic {} value = 19918300099`, async(() => {
    const res = validator.isNewNIC('19918300099');
    expect(res).toBeFalsy();
  }));

  it(`test is nic {} value = 772517622V`, async(() => {
    const res = validator.isOldNIC('772517622V');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622X`, async(() => {
    const res = validator.isOldNIC('772517622X');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622v`, async(() => {
    const res = validator.isOldNIC('772517622v');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 772517622x`, async(() => {
    const res = validator.isOldNIC('772517622x');
    expect(res).toBeTruthy();
  }));

  it(`test is nic {} value = 72517622V`, async(() => {
    const res = validator.isOldNIC('72517622V');
    expect(res).toBeFalsy();
  }));

  it(`test is mobile number {} value = 777123456`, async(() => {
    const res = validator.isMobileNumber(777123456);
    expect(res).toBeTruthy();
  }));

  it(`test is mobile number {} value = 77123456`, async(() => {
    const res = validator.isMobileNumber(77123456);
    expect(res).toBeFalsy();
  }));

  it(`test is mobile number {} value = 77.123456`, async(() => {
    const res = validator.isMobileNumber(77.123456);
    expect(res).toBeFalsy();
  }));
});
