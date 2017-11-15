package com.pass.reference;

public class People extends Animal {

	private static final long serialVersionUID = -5551141297232297754L;

	private final BigManSpecial bigManSpecial;

	public People(Animal animal) {
		bigManSpecial = new BigManSpecial(animal);
	}

	private static class BigManSpecial extends Animal {

		private static final long serialVersionUID = -1469033491592194428L;

		private final Animal animal;

		public BigManSpecial(Animal animal) {
			this.animal = animal;
		}

		private void changeAnimalName() {
			animal.setName("李四");
		}
	}

	public final void handleAnimalName() {
		// 调用私有方法
		handlePeopleName();
	}

	private void handlePeopleName() {
		// 更改对应的属性值
		bigManSpecial.changeAnimalName();
	}
}
