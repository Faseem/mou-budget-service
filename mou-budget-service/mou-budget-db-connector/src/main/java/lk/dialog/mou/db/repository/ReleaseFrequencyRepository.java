package lk.dialog.mou.db.repository;

import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.ReleaseFrequency;

/**
 * Created by Aux072 on 18/09/2018.
 */
public interface ReleaseFrequencyRepository {
    ReleaseFrequency getReleaseFrequencyById(Long releaseFrequencyId);
    ReleaseFrequency getReleaseFrequencyByKeys(ReleaseFrequency releaseFrequency);
    ReleaseFrequency addReleaseFrequency(ReleaseFrequency releaseFrequency);
}
