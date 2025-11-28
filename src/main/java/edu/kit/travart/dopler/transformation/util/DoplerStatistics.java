package edu.kit.travart.dopler.transformation.util;

import org.apache.logging.log4j.Logger;

import at.jku.cps.travart.core.common.IStatistics;
import edu.kit.dopler.model.Dopler;
import edu.kit.dopler.model.EnumerationDecision;

public class DoplerStatistics implements IStatistics<Dopler> {
	
	private static DoplerStatistics instance;

	private DoplerStatistics() {}

	public static DoplerStatistics getInstance() {
		if (instance == null) {
			instance = new DoplerStatistics();
		}
		return instance;
	}

	@Override
	public int getVariabilityElementsCount(Dopler model) {
		// TODO Is is the right way to calculate size for DOPLER decision models?
		// Previously: Only #decisions + #assets
		// Try to calculate the number of branches
		return model.getDecisions().stream().mapToInt(e -> {
			if (e instanceof EnumerationDecision) {
				return ((EnumerationDecision) e).getEnumeration().getEnumerationLiterals().size();
			} else {
				return 1;
			}
		}).sum() + model.getAssets().size();
	}

	@Override
	public int getConstraintsCount(Dopler model) {
		return model.getDecisions().size();
	}

	@Override
	public void logModelStatistics(Logger logger, Dopler model) {
		logger.info("TODO: Implement statistics overview message for DOPLER");
	}

}
