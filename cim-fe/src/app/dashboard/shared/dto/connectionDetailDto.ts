import {Expose, serialize} from "class-transformer";

export class ConnectionDetailDto {

  @Expose({name: 'pend'})
  pend: number;

  @Expose({name: 'npmt'})
  npmt: number;

  @Expose({name: 'conn'})
  conn: number;

  @Expose({name: 'totalConnected'})
  totalConnected: number;

  @Expose({name: 'totalDIsConnected'})
  totalDIsConnected: number;



  constructor(connectionDetailDto:any) {
    if(!connectionDetailDto){
      return;
    }
    this.setPEND(connectionDetailDto.pend);
    this.setNPMT(connectionDetailDto.npmt);
    this.setCONN(connectionDetailDto.conn);
    this.setTotalConnected(connectionDetailDto.totalConnected);
    this.setTotalDIsConnected(connectionDetailDto.totalDIsConnected);
  }

  setPEND(value: number) {
    this.pend = value;
  }

  setNPMT(value: number) {
    this.npmt = value;
  }

  setCONN(value: number) {
    this.conn = value;
  }

  setTotalConnected(value: number) {
    this.totalConnected = value;
  }

  setTotalDIsConnected(value: number) {
    this.totalDIsConnected = value;
  }


  toJSon(): any {
    return serialize(this);
  }
}
