import { TestBed } from '@angular/core/testing';

import { ListTurnService } from './list-turn.service';

describe('ListTurnService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ListTurnService = TestBed.get(ListTurnService);
    expect(service).toBeTruthy();
  });
});
