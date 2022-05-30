package cleverdearh.fourth.service.impl;

import cleverdearh.fourth.entity.Complex;
import cleverdearh.fourth.service.FilterService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterServiceImpl implements FilterService {

    public static final int N = 1024;

    @Override
    public List<Double> filterFirst(List<Double> in) {
        List<Double> out = new ArrayList<>(in.size());
        double f1 = 8;
        double fh = 20;
        double hl[] = new double[N];
        double hh[] = new double[N];
        double hRes[] = new double[N];
        double fcl = f1 / N;
        double fch = fh / N;
        for (int i = 0; i < N; i++) {
            if (i - N / 2 == 0) {
                hh[i] = 2 * Math.PI * fcl;
            } else {
                hl[i] = hl[i] * (0.54 - 0.46 * Math.cos((2 * Math.PI * i)) / N);
            }
            hl[i] = hl[i] * (0.54 - 0.46 * Math.cos((2 * Math.PI * i) / N));
        }
        for (int i = 0; i < N; i++) {
            if (i - N / 2 == 0) {
                hh[i] = 2 * Math.PI * fch;
            } else {
                hh[i] = Math.sin(2 * Math.PI * fch * (i - N / 2));
            }
            hh[i] = hh[i] * (0.54 - 0.46 * Math.cos((2 * Math.PI * i) / N));
        }
        double sum;
        sum = Arrays.stream(hh)
                .reduce(0, Double::sum);
        for (int i = 0; i < N; i++) {
            hh[i] /= -sum;
        }
        hh[N / 2]++;
        for (int i = 0; i < N; i++) {
            hRes[i] = hh[i] + hl[i];
        }
        for (int i = 0; i < in.size(); i++) {
            out.set(i, 0.);
            for (int j = 0; j < N - 1; j++) {
                if (i - j >= 0) {
                    out.add(i, hRes[j] * in.get(i - j));
                }
            }
        }
        return out;
    }

    @Override
    public void fft(List<Complex> a, boolean invert) {
        int n = a.size();
        if (n == 1) {
            return;
        }
        List<Complex> a0 = new ArrayList<>(n / 2);
        List<Complex> a1 = new ArrayList<>(n / 2);
        for (int i = 0, j = 0; i < n; i += 2, ++j) {
            a0.set(j, a.get(i));
            a1.set(j, a.get(i + 1));
        }
        fft(a0, invert);
        fft(a1, invert);

        double angle = 2 * Math.PI / n * (invert ? -1 : 1);
        Complex w = new Complex(1, 0);
        Complex wn = new Complex(Math.cos(angle), Math.sin(angle));
        for (int i = 0; i < n / 2; ++i) {
            a.set(i, w.mul(a1.get(i)).add(a0.get(i)));
            a.set(i + n / 2, a0.get(i).sub(w.mul(a1.get(i))));
            if (invert) {
                a.set(i, a.get(i).div(new Complex(2, a.get(i + n / 2).div(new Complex(2, 0)).module())));
            }
            w = w.mul(wn);
        }
    }

    @Override
    public List<Double> countAmplitude(List<Double> in) {
        return null;
    }
}
