export interface SelectConfiguration {
  fontSize: number;
  activateFormControl: boolean;
  formControl: any;
  options: selectOption[];
}

interface selectOption {
  value: any;
    label: string;
}
