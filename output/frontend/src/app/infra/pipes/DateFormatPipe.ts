import { Constants } from './constants';
import { Pipe, PipeTransform } from '@angular/core';
import { DatePipe } from '@angular/common';

@Pipe({
  name: 'dateFormat'
})
export class DateFormatPipe extends DatePipe implements PipeTransform {
  transform(value: any, args?: any): any {
    return super.transform(value, Constants.DATE_FMT);
  }
}

// today | dateFormat
// 30/Aug/2017

// today | dateTimeFormat
// 30/Aug/2017 10:00:00
