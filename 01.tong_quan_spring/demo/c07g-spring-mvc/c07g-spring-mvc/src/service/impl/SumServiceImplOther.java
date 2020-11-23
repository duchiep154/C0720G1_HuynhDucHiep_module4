package service.impl;

import org.springframework.stereotype.Service;
import service.SumService;

@Service
public class SumServiceImplOther implements SumService {
    @Override
    public int sum(int a, int b) {
        return (a + b) * 2;
    }
}
