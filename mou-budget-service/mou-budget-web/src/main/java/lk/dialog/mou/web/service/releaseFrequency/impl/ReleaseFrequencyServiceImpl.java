package lk.dialog.mou.web.service.releaseFrequency.impl;

import lk.dialog.mou.db.repository.BudgetTypeRepository;
import lk.dialog.mou.db.repository.ReleaseFrequencyRepository;
import lk.dialog.mou.domain.BudgetType;
import lk.dialog.mou.domain.ReleaseFrequency;
import lk.dialog.mou.web.service.budgetType.BudgetTypeService;
import lk.dialog.mou.web.service.releaseFrequency.ReleaseFrequencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Aux072 on 18/09/2018.
 */
@Service
public class ReleaseFrequencyServiceImpl implements ReleaseFrequencyService{

    @Autowired
    ReleaseFrequencyRepository releaseFrequencyRepository;

    @Override
    public ReleaseFrequency getReleaseFrequencyByKeys(ReleaseFrequency releaseFrequency) {
        return releaseFrequencyRepository.getReleaseFrequencyByKeys(releaseFrequency);
    }

    @Override
    public ReleaseFrequency addReleaseFrequency(ReleaseFrequency releaseFrequency) {
        return releaseFrequencyRepository.addReleaseFrequency(releaseFrequency);
    }
}
