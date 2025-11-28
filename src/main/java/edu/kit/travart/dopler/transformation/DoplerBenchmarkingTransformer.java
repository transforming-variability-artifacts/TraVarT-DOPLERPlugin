package edu.kit.travart.dopler.transformation;

import at.jku.cps.travart.core.common.IStatistics;
import at.jku.cps.travart.core.exception.NotSupportedVariabilityTypeException;
import de.vill.model.FeatureModel;
import edu.kit.dopler.model.Dopler;
import edu.kit.travart.dopler.transformation.decision.to.feature.DmToFmTransformer;
import edu.kit.travart.dopler.transformation.feature.to.decision.FmToDmTransformer;
import edu.kit.travart.dopler.transformation.util.DoplerStatistics;
import at.jku.cps.travart.core.transformation.*;

public class DoplerBenchmarkingTransformer extends AbstractBenchmarkingTransformer<Dopler> {

	private final DmToFmTransformer dmToFmTransformer;
    private final FmToDmTransformer fmToDmTransformer;

    /**
     * Constructor of {@link Transformer}.
     *
     * @param dmToFmTransformer {@link DmToFmTransformer}
     * @param fmToDmTransformer {@link FmToDmTransformer}
     */
    public DoplerBenchmarkingTransformer(DmToFmTransformer dmToFmTransformer, FmToDmTransformer fmToDmTransformer) {
        this.dmToFmTransformer = dmToFmTransformer;
        this.fmToDmTransformer = fmToDmTransformer;
    }

	@Override
	public Dopler transformInner(FeatureModel model, String modelName, STRATEGY strategy)
			throws NotSupportedVariabilityTypeException {
		return fmToDmTransformer.transform(model, strategy);
	}
	
	@Override
	public FeatureModel transformInner(Dopler model, String modelName, STRATEGY strategy)
			throws NotSupportedVariabilityTypeException {
		return dmToFmTransformer.transform(model, strategy);
	}
	
	@Override
	public IStatistics<Dopler> getTargetStatistics() {
		return DoplerStatistics.getInstance();
	}
}
