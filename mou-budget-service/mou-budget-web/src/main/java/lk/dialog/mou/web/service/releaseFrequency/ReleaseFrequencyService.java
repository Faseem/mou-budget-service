package lk.dialog.mou.web.service.releaseFrequency;

import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.ReleaseFrequency;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface ReleaseFrequencyService {
    ReleaseFrequency getReleaseFrequencyByKeys(ReleaseFrequency releaseFrequency);
    ReleaseFrequency addReleaseFrequency(ReleaseFrequency releaseFrequency);
}
