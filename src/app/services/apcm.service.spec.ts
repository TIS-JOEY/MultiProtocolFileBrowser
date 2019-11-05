import { TestBed } from '@angular/core/testing';

import { ApcmService } from './apcm.service';

describe('ApcmService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: ApcmService = TestBed.get(ApcmService);
    expect(service).toBeTruthy();
  });
});
