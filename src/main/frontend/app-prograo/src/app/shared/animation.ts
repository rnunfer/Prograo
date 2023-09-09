import { trigger, transition, style, animate, state, keyframes, AnimationTriggerMetadata } from '@angular/animations';

export const fadeIn = trigger('fadeIn', [
  transition('void => *', [
    style({ opacity: 0 }),
    animate(100, style({ opacity: 1 }))
  ])
]);

export const fadeOut = trigger('fadeOut', [
  transition('* => void', [
    animate(100, style({ opacity: 0 }))
  ])
]);

export const slideUp = trigger('slideUp', [
  state('void', style({ height: '0' })),
  state('*', style({ height: '*' })),
  transition('* => void', animate('500ms ease-out')),
  transition('void => *', animate('500ms ease-in'))
])

export const slideDown = trigger('slideDown', [
  state('void', style({ height: '0' })),
  state('*', style({ height: '*' })),
  transition('void => *', animate('500ms ease-in')),
  transition('* => void', animate('500ms ease-out'))
])
