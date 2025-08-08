function CategoryFilter({ categories, selectedCategories, onToggle, onClear }) {
  return (
    <div className="categories-list">
      {categories.map((cat) => (
        <label
          key={cat.id}
          className="category-item d-flex align-items-center"
        >
          <input
            type="checkbox"
            className="form-check-input me-2"
            checked={selectedCategories.includes(cat.name)}
            onChange={() => onToggle(cat.name)}
          />
          {cat.name}
        </label>
      ))}
      {selectedCategories.length > 0 && (
        <button
          type="button"
          className="btn btn-link mt-2 p-0"
          onClick={onClear}
        >
          Limpiar filtros
        </button>
      )}
    </div>
  );
}

export default CategoryFilter;
