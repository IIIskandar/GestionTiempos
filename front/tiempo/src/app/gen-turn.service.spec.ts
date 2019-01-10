import { TestBed } from '@angular/core/testing';

import { GenTurnService } from './gen-turn.service';

describe('GenTurnService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GenTurnService = TestBed.get(GenTurnService);
    expect(service).toBeTruthy();
  });
});
