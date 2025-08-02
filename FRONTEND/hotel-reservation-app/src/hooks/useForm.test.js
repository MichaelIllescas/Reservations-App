import { renderHook, act } from '@testing-library/react';
import { vi } from 'vitest';
import useForm from './useForm';

test('handleChange updates form data', () => {
  const { result } = renderHook(() => useForm({ name: '' }, () => ({})));
  act(() => {
    result.current.handleChange({ target: { name: 'name', value: 'John' } });
  });
  expect(result.current.formData.name).toBe('John');
});

test('handleSubmit triggers callback when valid', () => {
  const callback = vi.fn();
  const { result } = renderHook(() => useForm({ email: '' }, () => ({})));
  act(() => {
    result.current.handleSubmit(callback)({ preventDefault: () => {} });
  });
  expect(callback).toHaveBeenCalled();
});
