import { Validators } from '@angular/forms';
import { FormlyFieldConfig } from '@ngx-formly/core';
import { LoginValidators } from './LoginValidators';

export const FORM_LOGIN_VALIDATORS: FormlyFieldConfig[] = [
  {
    key: 'username',
    type: 'input',
    templateOptions: {
      label: 'Username',
      required: true,
    },
    validators: {
      validation: [Validators.required, LoginValidators.usernameLength],
    },
    validation: {
      messages: {
        required: 'Username is required',
        usernameLength: 'Username must be between 1 and 10 characters.',
      },
    },
  },
];
