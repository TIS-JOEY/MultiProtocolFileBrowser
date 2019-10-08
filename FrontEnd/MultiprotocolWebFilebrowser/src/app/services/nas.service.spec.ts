import { TestBed } from '@angular/core/testing';

import { NasService } from './nas.service';

describe('NasService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: NasService = TestBed.get(NasService);
    expect(service).toBeTruthy();
  });
});
