package cleverdearh.fourth.service;


import cleverdearh.fourth.entity.Complex;

import java.util.List;

public interface FilterService {
    List<Double> filterFirst(List<Double> in);
    void fft(List<Complex> a, boolean invert);
    List<Double> countAmplitude(List<Double> in);
}
