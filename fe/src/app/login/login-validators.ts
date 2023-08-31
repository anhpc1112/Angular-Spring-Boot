import { AbstractControl, ValidationErrors } from '@angular/forms';

export class LoginValidators {
  static usernameLength(control: AbstractControl): ValidationErrors | null {
    const value: string = control.value;
    if (value && (value.length < 1 || value.length > 10)) {
      return { usernameLength: true };
    }
    return null;
  }
}
