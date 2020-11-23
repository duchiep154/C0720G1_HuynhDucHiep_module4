package service.impl;

import org.springframework.stereotype.Service;
import service.SumService;

@Service
public class SumServiceImpl implements SumService {

    @Override
    public int sum(int a, int b) {
        return a + b;
    }
}
